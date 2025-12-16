package com.example.demobase.service;

import com.example.demobase.dto.WordDTO;
import com.example.demobase.model.Word;
import com.example.demobase.repository.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WordServiceTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordService wordService;

    private Word word1;
    private Word word2;
    private Word word3;

    @BeforeEach
    void setUp() {
        word1 = new Word(1L, "PROGRAMADOR", true);
        word2 = new Word(2L, "COMPUTADORA", false);
        word3 = new Word(3L, "TECNOLOGIA", false);
    }

    @Test
    void testGetAllWords() {
        // TODO: Implementar el test para getAllWords
        WordService spyService = Mockito.spy(wordService);


        List<Word> words = List.of(word2, word3);


        Mockito.when(wordRepository.findAllOrdered())
                .thenReturn(words);

        WordDTO dto1 = new WordDTO(1L, "COMPUTADORA", false);
        WordDTO dto2 = new WordDTO(2L, "TECNOLOGIA", false);

        Mockito.doReturn(dto1).when(spyService).toDTO(word2);
        Mockito.doReturn(dto2).when(spyService).toDTO(word3);


        List<WordDTO> resultado = spyService.getAllWords();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        assertEquals("COMPUTADORA", resultado.get(0).getPalabra());
        assertEquals("TECNOLOGIA", resultado.get(1).getPalabra());

        Mockito.verify(wordRepository).findAllOrdered();
        Mockito.verify(spyService).toDTO(word2);
        Mockito.verify(spyService).toDTO(word3);
        
    }

    @Test
    void testGetAllWords_EmptyList() {
        // TODO: Implementar el test para getAllWords_EmptyList
        
    }
}

