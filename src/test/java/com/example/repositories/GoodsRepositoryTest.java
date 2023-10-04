package com.example.repositories;

import com.example.models.Category;
import com.example.models.Goods;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GoodsRepositoryTest {

    @Mock
    private GoodsRepository goodsRepositoryMock;

    @Test
    public void testFindByCategoryId() {
        int categoryId = 1;
        List<Goods> expectedGoodsList = new ArrayList<>();
        Goods goods1 = new Goods();
        goods1.setCategory(new Category());
        goods1.setId(categoryId);
        expectedGoodsList.add(goods1);
        Goods goods2 = new Goods();
        goods2.setCategory(new Category());
        goods2.setId(categoryId);
        expectedGoodsList.add(goods2);

        when(goodsRepositoryMock.findByCategoryId((long)categoryId)).thenReturn(expectedGoodsList);

        List<Goods> actualGoodsList = goodsRepositoryMock.findByCategoryId((long)categoryId);

        assertEquals(expectedGoodsList.size(), actualGoodsList.size());
        assertEquals(expectedGoodsList.get(0).getId(), actualGoodsList.get(0).getId());
        assertEquals(expectedGoodsList.get(1).getId(), actualGoodsList.get(1).getId());
    }

    @Test
    public void testSave() {
        Goods goodsToSave = new Goods();
        goodsToSave.setId(1);

        when(goodsRepositoryMock.save(goodsToSave)).thenReturn(goodsToSave);

        Goods savedGoods = goodsRepositoryMock.save(goodsToSave);

        assertEquals(goodsToSave.getId(), savedGoods.getId());
    }

    @Test
    public void testDelete() {
        Goods goodsToDelete = new Goods();
        goodsToDelete.setId(1);

        goodsRepositoryMock.delete(goodsToDelete);

        Mockito.verify(goodsRepositoryMock).delete(goodsToDelete);
    }
}
