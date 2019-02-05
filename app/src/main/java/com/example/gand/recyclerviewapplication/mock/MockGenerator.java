package com.example.gand.recyclerviewapplication.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MockGenerator {

    public static List<Mock> generate(int coint){
        List<Mock> mocks = new ArrayList<>(coint);
        Random random = new Random();
        for (int i = 0; i < coint; i++) {
            mocks.add(new Mock(UUID.randomUUID().toString(), random.nextInt(200)));
        }
        return mocks;
    }
}
