package com.bondarenko.memoryanalyzer.service;

import com.bondarenko.memoryanalyzer.entity.Data;
import com.bondarenko.memoryanalyzer.repository.DataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Slf4j
@Service
@AllArgsConstructor
public class DataService implements DataServiceImpl {
    private DataRepository repository;
    private static final int PAGE_SIZE = 100;

    @Transactional
    @Override
    public void process() {
        int page = 0;
        Page<Data> currentPage;
        do {
            currentPage = repository.findAll(PageRequest.of(page, PAGE_SIZE));
            List<Data> dataList = currentPage.getContent();

            for (Data data : dataList) {
                log.info("Modification data id= " + data.getId());
                double dataSizeInKB = getDataSizeInKB(data);
                data.setModificationData(LocalDateTime.now());
            }

            repository.saveAll(dataList);
            page++;
            log.info("Save data to the database ");

        } while (!currentPage.isEmpty());
    }

    private static double getDataSizeInKB(Data data) {
        final double byteToKb = 1024.0;
        long dataSizeInBytes = data.getData().getBytes().length;
        return dataSizeInBytes / byteToKb;
    }

    @Override
    public void generateAndSaveData() {
        List<Data> dataList = IntStream.range(0, 1000)
                .mapToObj(row -> {
                    return getData();
                }).collect(Collectors.toList());
        repository.saveAll(dataList);

    }

    private Data getData() {
        Data data = new Data();
        data.setData(generateRandomString(500000));
        data.setModificationData(LocalDateTime.now());
        return data;
    }

    private String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();
        return random.ints(length, 0, characters.length())
                .mapToObj(characters::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
