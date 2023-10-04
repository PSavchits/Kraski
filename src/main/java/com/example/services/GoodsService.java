package com.example.services;

import com.example.models.Category;
import com.example.models.Goods;
import com.example.models.GoodsAttributes;
import com.example.repositories.GoodsAttributesRepository;
import com.example.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class GoodsService {
    private final GoodsRepository goodsRepository;

    private final GoodsAttributesRepository goodsAttributesRepository;

    private final GoodsAttributesService goodsAttributesService;

    @Autowired
    public GoodsService(GoodsRepository goodsRepository, GoodsAttributesRepository goodsAttributesRepository, GoodsAttributesService goodsAttributesService) {
        this.goodsRepository = goodsRepository;
        this.goodsAttributesRepository = goodsAttributesRepository;
        this.goodsAttributesService = goodsAttributesService;
    }

    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public Goods findOne(int id) {
        return goodsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveProduct(Goods goods, GoodsAttributes goodsAttributes, MultipartFile imageFile, Category selectedCategory) throws IOException {
        setDefaultDateAdded(goods);

        GoodsAttributes savedAttributes = goodsAttributesRepository.save(goodsAttributes);
        goods.setAttributes(savedAttributes);

        goods.setCategory(selectedCategory);

        Goods savedGoods = goodsRepository.save(goods);

        updateImage(savedGoods, imageFile);
    }

    @Transactional
    public void updateProduct(Goods goods, MultipartFile imageFile, int id) throws IOException {
        goods.setDateUpdated(new Date());
        Goods existingGoods = goodsRepository.findById(id).orElse(null);
        GoodsAttributes existingAttributes = goodsAttributesRepository.findById(id);

        existingGoods.setProductName(goods.getProductName());
        existingAttributes.setColor(goods.getAttributes().getColor());
        existingAttributes.setCountry(goods.getAttributes().getCountry());
        //TODO заменить сеттеры на использование MapStruct

        GoodsAttributes savedAttributes = goodsAttributesRepository.save(existingAttributes);

        existingGoods.setCategory(goods.getCategory());
        existingGoods.setAttributes(savedAttributes);

        Goods updatedGoods = goodsRepository.save(existingGoods);


        updateImage(updatedGoods, imageFile);
    }

    private void setDefaultDateAdded(Goods goods) {
        if (goods.getDateAdded() == null) {
            goods.setDateAdded(new Date());
        }
    }

    public void updateImage(Goods goods, MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            deleteImageIfExists(goods.getImageFilename());

            String imageName = generateImageName(goods.getId(), Objects.requireNonNull(imageFile.getOriginalFilename()));
            String imagePath = "static/images";
            Path imageFilePath = Paths.get(imagePath, imageName);
            createImageDirectory(imageFilePath);

            try {
                Files.write(imageFilePath, imageFile.getBytes());
                goods.setImageFilename(imageName);
                goodsRepository.save(goods);
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Failed to save image: " + e.getMessage());
            }
        }
    }

    private String generateImageName(Integer goodsId, String originalFileName) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        return "product_" + goodsId + "." + fileExtension;
    }

    private void createImageDirectory(Path imageFilePath) throws IOException {
        Files.createDirectories(imageFilePath.getParent());
    }

    private void deleteImageIfExists(String imageName) throws IOException {
        if (imageName != null) {
            String imagePath = "static/images";
            Path imageFilePath = Paths.get(imagePath, imageName);
            Files.deleteIfExists(imageFilePath);
        }
    }
    public List<Goods> findByCategoryId(Long id) {
        return goodsRepository.findByCategoryId(id);
    }
}
