package com.example.repositories;

import com.example.models.GoodsAttributes;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GoodsAttributesRepositoryTest {

    @Mock
    private GoodsAttributesRepository goodsAttributesRepositoryMock;

    @Test
    public void testFindById() {
        int id = 1;
        GoodsAttributes expectedAttributes = new GoodsAttributes();
        expectedAttributes.setId(id);

        when(goodsAttributesRepositoryMock.findById(id)).thenReturn(expectedAttributes);

        GoodsAttributes actualAttributes = goodsAttributesRepositoryMock.findById(id);

        assertEquals(expectedAttributes.getId(), actualAttributes.getId());
    }

    @Test
    public void testSave() {
        GoodsAttributes attributesToSave = new GoodsAttributes();
        attributesToSave.setId(1);

        when(goodsAttributesRepositoryMock.save(attributesToSave)).thenReturn(attributesToSave);

        GoodsAttributes savedAttributes = goodsAttributesRepositoryMock.save(attributesToSave);

        assertEquals(attributesToSave.getId(), savedAttributes.getId());
    }

    @Test
    public void testDelete() {
        int id = 1;
        GoodsAttributes attributesToDelete = new GoodsAttributes();
        attributesToDelete.setId(id);

        goodsAttributesRepositoryMock.delete(attributesToDelete);

        Mockito.verify(goodsAttributesRepositoryMock).delete(attributesToDelete);
    }
}
