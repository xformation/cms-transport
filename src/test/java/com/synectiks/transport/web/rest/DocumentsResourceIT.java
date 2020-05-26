package com.synectiks.transport.web.rest;

import com.synectiks.transport.TransportApp;
import com.synectiks.transport.domain.Documents;
import com.synectiks.transport.repository.DocumentsRepository;
import com.synectiks.transport.service.DocumentsService;
import com.synectiks.transport.service.dto.DocumentsDTO;
import com.synectiks.transport.service.mapper.DocumentsMapper;
import com.synectiks.transport.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.synectiks.transport.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DocumentsResource} REST controller.
 */
@SpringBootTest(classes = TransportApp.class)
public class DocumentsResourceIT {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_MS_ONE_DRIVE_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_MS_ONE_DRIVE_FILE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_JSR_OAK_REPO_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_JSR_OAK_REPO_FILE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_AWS_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_AWS_FILE_PATH = "BBBBBBBBBB";

    @Autowired
    private DocumentsRepository documentsRepository;

    @Autowired
    private DocumentsMapper documentsMapper;

    @Autowired
    private DocumentsService documentsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restDocumentsMockMvc;

    private Documents documents;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DocumentsResource documentsResource = new DocumentsResource(documentsService);
        this.restDocumentsMockMvc = MockMvcBuilders.standaloneSetup(documentsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Documents createEntity(EntityManager em) {
        Documents documents = new Documents()
            .fileName(DEFAULT_FILE_NAME)
            .fileType(DEFAULT_FILE_TYPE)
            .filePath(DEFAULT_FILE_PATH)
            .msOneDriveFilePath(DEFAULT_MS_ONE_DRIVE_FILE_PATH)
            .jsrOakRepoFilePath(DEFAULT_JSR_OAK_REPO_FILE_PATH)
            .awsFilePath(DEFAULT_AWS_FILE_PATH);
        return documents;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Documents createUpdatedEntity(EntityManager em) {
        Documents documents = new Documents()
            .fileName(UPDATED_FILE_NAME)
            .fileType(UPDATED_FILE_TYPE)
            .filePath(UPDATED_FILE_PATH)
            .msOneDriveFilePath(UPDATED_MS_ONE_DRIVE_FILE_PATH)
            .jsrOakRepoFilePath(UPDATED_JSR_OAK_REPO_FILE_PATH)
            .awsFilePath(UPDATED_AWS_FILE_PATH);
        return documents;
    }

    @BeforeEach
    public void initTest() {
        documents = createEntity(em);
    }

    @Test
    @Transactional
    public void createDocuments() throws Exception {
        int databaseSizeBeforeCreate = documentsRepository.findAll().size();

        // Create the Documents
        DocumentsDTO documentsDTO = documentsMapper.toDto(documents);
        restDocumentsMockMvc.perform(post("/api/documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(documentsDTO)))
            .andExpect(status().isCreated());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeCreate + 1);
        Documents testDocuments = documentsList.get(documentsList.size() - 1);
        assertThat(testDocuments.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testDocuments.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testDocuments.getFilePath()).isEqualTo(DEFAULT_FILE_PATH);
        assertThat(testDocuments.getMsOneDriveFilePath()).isEqualTo(DEFAULT_MS_ONE_DRIVE_FILE_PATH);
        assertThat(testDocuments.getJsrOakRepoFilePath()).isEqualTo(DEFAULT_JSR_OAK_REPO_FILE_PATH);
        assertThat(testDocuments.getAwsFilePath()).isEqualTo(DEFAULT_AWS_FILE_PATH);
    }

    @Test
    @Transactional
    public void createDocumentsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = documentsRepository.findAll().size();

        // Create the Documents with an existing ID
        documents.setId(1L);
        DocumentsDTO documentsDTO = documentsMapper.toDto(documents);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentsMockMvc.perform(post("/api/documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(documentsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDocuments() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList
        restDocumentsMockMvc.perform(get("/api/documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documents.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE)))
            .andExpect(jsonPath("$.[*].filePath").value(hasItem(DEFAULT_FILE_PATH)))
            .andExpect(jsonPath("$.[*].msOneDriveFilePath").value(hasItem(DEFAULT_MS_ONE_DRIVE_FILE_PATH)))
            .andExpect(jsonPath("$.[*].jsrOakRepoFilePath").value(hasItem(DEFAULT_JSR_OAK_REPO_FILE_PATH)))
            .andExpect(jsonPath("$.[*].awsFilePath").value(hasItem(DEFAULT_AWS_FILE_PATH)));
    }
    
    @Test
    @Transactional
    public void getDocuments() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get the documents
        restDocumentsMockMvc.perform(get("/api/documents/{id}", documents.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(documents.getId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE))
            .andExpect(jsonPath("$.filePath").value(DEFAULT_FILE_PATH))
            .andExpect(jsonPath("$.msOneDriveFilePath").value(DEFAULT_MS_ONE_DRIVE_FILE_PATH))
            .andExpect(jsonPath("$.jsrOakRepoFilePath").value(DEFAULT_JSR_OAK_REPO_FILE_PATH))
            .andExpect(jsonPath("$.awsFilePath").value(DEFAULT_AWS_FILE_PATH));
    }

    @Test
    @Transactional
    public void getNonExistingDocuments() throws Exception {
        // Get the documents
        restDocumentsMockMvc.perform(get("/api/documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDocuments() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();

        // Update the documents
        Documents updatedDocuments = documentsRepository.findById(documents.getId()).get();
        // Disconnect from session so that the updates on updatedDocuments are not directly saved in db
        em.detach(updatedDocuments);
        updatedDocuments
            .fileName(UPDATED_FILE_NAME)
            .fileType(UPDATED_FILE_TYPE)
            .filePath(UPDATED_FILE_PATH)
            .msOneDriveFilePath(UPDATED_MS_ONE_DRIVE_FILE_PATH)
            .jsrOakRepoFilePath(UPDATED_JSR_OAK_REPO_FILE_PATH)
            .awsFilePath(UPDATED_AWS_FILE_PATH);
        DocumentsDTO documentsDTO = documentsMapper.toDto(updatedDocuments);

        restDocumentsMockMvc.perform(put("/api/documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(documentsDTO)))
            .andExpect(status().isOk());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
        Documents testDocuments = documentsList.get(documentsList.size() - 1);
        assertThat(testDocuments.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testDocuments.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testDocuments.getFilePath()).isEqualTo(UPDATED_FILE_PATH);
        assertThat(testDocuments.getMsOneDriveFilePath()).isEqualTo(UPDATED_MS_ONE_DRIVE_FILE_PATH);
        assertThat(testDocuments.getJsrOakRepoFilePath()).isEqualTo(UPDATED_JSR_OAK_REPO_FILE_PATH);
        assertThat(testDocuments.getAwsFilePath()).isEqualTo(UPDATED_AWS_FILE_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingDocuments() throws Exception {
        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();

        // Create the Documents
        DocumentsDTO documentsDTO = documentsMapper.toDto(documents);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentsMockMvc.perform(put("/api/documents")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(documentsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDocuments() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        int databaseSizeBeforeDelete = documentsRepository.findAll().size();

        // Delete the documents
        restDocumentsMockMvc.perform(delete("/api/documents/{id}", documents.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
