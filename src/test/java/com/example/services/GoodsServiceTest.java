package com.example.services;

import com.example.models.Category;
import com.example.models.Goods;
import com.example.models.GoodsAttributes;
import com.example.repositories.GoodsAttributesRepository;
import com.example.repositories.GoodsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GoodsServiceTest {

    @Mock
    private GoodsRepository goodsRepository;

    @Mock
    private GoodsAttributesRepository goodsAttributesRepository;

    @Mock
    private GoodsAttributesService goodsAttributesService;

    @Mock
    private GoodsService goodsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        goodsService = new GoodsService(goodsRepository, goodsAttributesRepository, goodsAttributesService);
        when(goodsAttributesRepository.save(any(GoodsAttributes.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }


    @Test
    void findAll_ShouldReturnAllGoods() {
        List<Goods> expectedGoods = new ArrayList<>();
        expectedGoods.add(new Goods());
        expectedGoods.add(new Goods());

        when(goodsRepository.findAll()).thenReturn(expectedGoods);

        List<Goods> actualGoods = goodsService.findAll();

        assertThat(actualGoods).isEqualTo(expectedGoods);
        verify(goodsRepository).findAll();
    }

    @Test
    void findOne_WithValidId_ShouldReturnCorrespondingGoods() {
        int goodsId = 1;
        Goods expectedGoods = new Goods();
        expectedGoods.setId(goodsId);

        when(goodsRepository.findById(goodsId)).thenReturn(java.util.Optional.of(expectedGoods));

        Goods actualGoods = goodsService.findOne(goodsId);

        assertThat(actualGoods).isEqualTo(expectedGoods);
        verify(goodsRepository).findById(goodsId);
    }

    @Test
    void findOne_WithInvalidId_ShouldReturnNull() {
        int goodsId = 1;

        when(goodsRepository.findById(goodsId)).thenReturn(java.util.Optional.empty());

        Goods actualGoods = goodsService.findOne(goodsId);

        assertThat(actualGoods).isNull();
        verify(goodsRepository).findById(goodsId);
    }

    @Test
    void saveProduct_ShouldSaveProductWithAttributesAndImage() throws IOException {
        Goods goods = new Goods();
        GoodsAttributes goodsAttributes = new GoodsAttributes();
        MultipartFile imageFile = new MockMultipartFile("image.jpg", new byte[0]);
        Category selectedCategory = new Category();

        when(goodsAttributesRepository.save(goodsAttributes)).thenReturn(goodsAttributes);
        when(goodsRepository.save(goods)).thenReturn(goods);

        goodsService.saveProduct(goods, goodsAttributes, imageFile, selectedCategory);

        verify(goodsAttributesRepository).save(goodsAttributes);
        verify(goodsRepository).save(goods);
    }

    @Test
    void updateProduct_ShouldUpdateProductWithAttributesAndImage() throws IOException {
        int goodsId = 1;
        Goods goods = new Goods();
        goods.setId(goodsId);

        GoodsAttributes goodsAttributes = new GoodsAttributes();
        goodsAttributes.setColor("Red");
        goods.setAttributes(goodsAttributes);

        MultipartFile imageFile = new MockMultipartFile("image.jpg", new byte[0]);

        Goods existingGoods = new Goods();
        existingGoods.setId(goodsId);

        GoodsAttributes existingAttributes = new GoodsAttributes();
        existingAttributes.setId(goodsId);

        when(goodsRepository.findById(goodsId)).thenReturn(java.util.Optional.of(existingGoods));
        when(goodsAttributesRepository.findById(goodsId)).thenReturn(existingAttributes);
        when(goodsAttributesRepository.save(existingAttributes)).thenReturn(existingAttributes);
        when(goodsRepository.save(existingGoods)).thenReturn(existingGoods);

        goodsService.updateProduct(goods, imageFile, goodsId);

        assertThat(existingGoods.getDateUpdated()).isNull();
        assertThat(existingGoods.getProductName()).isEqualTo(goods.getProductName());
        assertThat(existingAttributes.getColor()).isEqualTo(goods.getAttributes().getColor());
        assertThat(existingAttributes.getCountry()).isEqualTo(goods.getAttributes().getCountry());
        verify(goodsRepository).findById(goodsId);
        verify(goodsAttributesRepository).findById(goodsId);
        verify(goodsAttributesRepository).save(existingAttributes);
        verify(goodsRepository).save(existingGoods);
    }

}