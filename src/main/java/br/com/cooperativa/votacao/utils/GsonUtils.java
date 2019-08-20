package br.com.cooperativa.votacao.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.OffsetDateTime;

public class GsonUtils {

    public static String objectToString(Object o) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new TypeAdapter<OffsetDateTime>() {
                    @Override
                    public void write(JsonWriter out, OffsetDateTime value) throws IOException {
                        out.value(value.toString());
                    }

                    @Override
                    public OffsetDateTime read(JsonReader in) throws IOException {
                        return OffsetDateTime.parse(in.nextString());
                    }
                })
                .enableComplexMapKeySerialization()
                .create();
        return gson.toJson(o);
    }

    public static <T extends Object> T stringToObject(String s, Class<T> type) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new TypeAdapter<OffsetDateTime>() {
                    @Override
                    public void write(JsonWriter out, OffsetDateTime value) throws IOException {
                        out.value(value.toString());
                    }

                    @Override
                    public OffsetDateTime read(JsonReader in) throws IOException {
                        return OffsetDateTime.parse(in.nextString());
                    }
                })
                .enableComplexMapKeySerialization()
                .create();
        return gson.fromJson(s, type);
    }
}
