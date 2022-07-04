package com.evelyn.exceptionHandling;

import java.io.BufferedReader;
import java.io.IOException;

public class JavaException {
    public static void main(String[] args) {

    }

    // java에서는 checked exception에 대해 메서드 뒤에 throws나 try~catch 처리를 강제한다.
    public Integer check(BufferedReader reader) throws IOException {
            String line = reader.readLine();
            return Integer.parseInt(line); // 숫자형식이 아닌 경우 에러 발생.
    }
}
