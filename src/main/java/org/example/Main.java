package org.example;

import org.example.model.Input;
import org.example.model.Operator;
import org.example.model.Output;
import org.example.service.CalculatorService;
import org.example.service.CalculatorServiceImpl;
import org.example.service.History;
import org.example.service.HistoryImpl;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculatorService calculatorService = new CalculatorServiceImpl();
        History history = new HistoryImpl();

        while (true) {
            System.out.println("===== 계산기 프로그램 =====");
            System.out.println("1. 계산");
            System.out.println("2. 기록");
            System.out.println("3. CSV 다운로드");
            System.out.println("4. 종료");
            System.out.print("메뉴 선택: ");

            int menu;
            try {
                menu = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자로 입력해주세요.\n");
                scanner.nextLine();
                continue;
            }

            if (menu == 4) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            switch (menu) {
                case 1 -> runCalculation(scanner, calculatorService, history);
                case 2 -> printHistory(history);
//                case 3 -> downloadCsv(history);
                default -> System.out.println("1~4 사이의 숫자를 입력해주세요.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void runCalculation(Scanner scanner, CalculatorService service, History history) {

        System.out.print("첫 번째 숫자: ");
        int a = scanner.nextInt();

        System.out.print("연산자 (+, -, *, /): ");
        String symbol = scanner.next();
        Operator operator = Arrays.stream(Operator.values())
                .filter(op -> op.getOperator().equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 연산자: " + symbol));

        System.out.print("두 번째 숫자: ");
        int b = scanner.nextInt();

        Output output = service.calculate(new Input(a, b, operator));
        history.addHistory(output);
        System.out.println(output);
    }

    private static void printHistory(History history) {
        List<Output> list = history.getHistory();
        if (list.isEmpty()) {
            System.out.println("저장된 기록이 없습니다.");
            return;
        }
        System.out.println("----- 계산 기록 -----");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }
}
