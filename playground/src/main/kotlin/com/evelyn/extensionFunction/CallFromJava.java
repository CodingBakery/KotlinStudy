package com.evelyn.extensionFunction;

/**
 * 자바에서 확장 함수 호출
 *
 * 내부적으로 확장 함수는 수신 객체를 첫번째 파라미터로 받는 정적 메소드이기 때문에
 * 단순히, 정적메소드를 호출하면서 첫번째 파라미터로 수신 객체를 넘기기만 하면 된다.
 *
 * 또한, 확장 함수가 들어있는 파일 이름에 따라 자바 클래스 이름이 결정된다. (ExtensionFunction.kt -> ExtenskonFunctionKt.class)
 */
public class CallFromJava {
    public static void main(String[] args) {
        ExtensionFunctionKt.convert("hello~~");
    }
}
