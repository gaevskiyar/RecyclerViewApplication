package com.example.gand.recyclerviewapplication.mock;

import com.example.gand.recyclerviewapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.example.gand.recyclerviewapplication.RecyclerFragment.IMAGE;


public class MockGenerator {

    public static Object generate(int type){
        Random random = new Random();
        Object mock;
        switch (type){
            case IMAGE:
                mock = new ImgMock(UUID.randomUUID().toString(), 0x2F000000+random.nextInt(0xFFFFFF));
                break;
            default:
                mock = new Mock(UUID.randomUUID().toString(), random.nextInt(200));
        }
        return mock;
    }
}
