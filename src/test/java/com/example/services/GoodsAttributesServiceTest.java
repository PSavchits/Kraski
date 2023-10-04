package com.example.services;

import com.example.models.GoodsAttributes;
import com.example.repositories.GoodsAttributesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GoodsAttributesServiceTest {

    @Mock
    private GoodsAttributesRepository goodsAttributesRepository;

    private GoodsAttributesService goodsAttributesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        goodsAttributesService = new GoodsAttributesService(goodsAttributesRepository);
    }

    @Test
    void saveGoodsAttributes_ShouldSaveAttributes() {
        // Arrange
        GoodsAttributes attributes = new GoodsAttributes();

        when(goodsAttributesRepository.save(attributes)).thenReturn(attributes);

        // Act
        GoodsAttributes savedAttributes = goodsAttributesService.saveGoodsAttributes(attributes);

        // Assert
        assertThat(savedAttributes).isEqualTo(attributes);
        verify(goodsAttributesRepository).save(attributes);
    }

    @Test
    void getAttributesByGoodsId_WithValidId_ShouldReturnAttributes() {
        // Arrange
        int goodsId = 1;
        GoodsAttributes expectedAttributes = new GoodsAttributes();
        expectedAttributes.setId(goodsId);

        // Указываем, что findById должен возвращать ожидаемые атрибуты
        when(goodsAttributesRepository.findById(goodsId)).thenReturn(expectedAttributes);

        // Act
        GoodsAttributes actualAttributes = goodsAttributesService.getAttributesByGoodsId(goodsId);

        // Assert
        assertThat(actualAttributes).isEqualTo(expectedAttributes);
        verify(goodsAttributesRepository).findById(goodsId);
    }

    @Test
    void getAttributesByGoodsId_WithInvalidId_ShouldReturnNull() {
        // Arrange
        int goodsId = 0;

        when(goodsAttributesRepository.findById(goodsId)).thenReturn(null);

        // Act
        GoodsAttributes actualAttributes = goodsAttributesService.getAttributesByGoodsId(goodsId);

        // Assert
        assertThat(actualAttributes).isNull();
    }

}