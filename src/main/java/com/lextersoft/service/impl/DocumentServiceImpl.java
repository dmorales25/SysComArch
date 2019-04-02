package com.lextersoft.service.impl;

import com.lextersoft.service.DocumentService;
import com.lextersoft.domain.Document;
import com.lextersoft.repository.DocumentRepository;
import com.lextersoft.service.FileManagerService;
import com.lextersoft.service.PlagiarismCheckerService;
import com.lextersoft.service.dto.DocumentDTO;
import com.lextersoft.service.mapper.DocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Implementation for managing Document.
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private final DocumentRepository documentRepository;
    private final FileManagerService fileManagerService;
    private final PlagiarismCheckerService plagiarismCheckerService;

    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, FileManagerService fileManagerService, PlagiarismCheckerService plagiarismCheckerService, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.fileManagerService = fileManagerService;
        this.plagiarismCheckerService = plagiarismCheckerService;
        this.documentMapper = documentMapper;
    }

    /**
     * Save a document.
     *
     * @param documentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DocumentDTO save(DocumentDTO documentDTO) {
        log.debug("Request to save Document : {}", documentDTO);

        Document document = documentMapper.toEntity(documentDTO);


        document.setUploadDate(LocalDate.now());
        document = documentRepository.save(document);


        DocumentDTO documentObj = documentMapper.toDto(document);

        return documentObj;
    }

    /**
     * Get all the documents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DocumentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Documents");
        return documentRepository.findAll(pageable)
            .map(documentMapper::toDto);
    }


    /**
     * Get one document by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentDTO> findOne(Long id) {
        log.debug("Request to get Document : {}", id);
        Optional<DocumentDTO> documentDTO = documentRepository.findById(id)
            .map(documentMapper::toDto);

        String text = fileManagerService.readFile(documentDTO.get().getArchiveName());
        try {
            documentDTO.get().setTextQueries(plagiarismCheckerService.process(text));
        }
        catch (IOException ex) {
            log.error(ex.toString());
        }
        return documentDTO;
    }

    /**
     * Delete the document by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Document : {}", id);
        documentRepository.deleteById(id);
    }
}
