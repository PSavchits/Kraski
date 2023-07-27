package com.example.services;

import com.example.models.Goods;
import com.example.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GoodsService {
    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public Goods findOne(int id){
        Optional<Goods> foundGoods = goodsRepository.findById(id);
        return foundGoods.orElse(null);
    }

    @Transactional
    public void saveProduct(Goods goods, MultipartFile imageFile) throws IOException {
        Goods savedGoods = goodsRepository.save(goods);

        if (!imageFile.isEmpty()) {
            String imageName = "product_" + savedGoods.getId() + ".jpg";
            String imagePath = "src/main/resources/static/images";
            Path imageFilePath = Paths.get(imagePath, imageName);
            Files.createDirectories(imageFilePath.getParent());

            try {
                Files.write(imageFilePath, imageFile.getBytes());
                savedGoods.setImageFilename(imageName);
                goodsRepository.save(savedGoods);
            } catch (IOException e) {
                //TODO handle errors
                e.printStackTrace();
                throw new IOException("Failed to save image: " + e.getMessage());
            }
        }
    }
}
