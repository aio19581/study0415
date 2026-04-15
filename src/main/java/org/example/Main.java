package org.example;

import org.example.Exception.CustomException;
import org.example.Exception.ExceptionType;
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
                menu = validateInput(scanner);
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.\n");
                scanner.nextLine();
                continue;
            } catch (CustomException e){
                System.out.println(e.getMessage() + "\n");
                scanner.nextLine();
                continue;
            }

            if (menu == 4) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            try {
                switch (menu) {
                    case 1 -> runCalculation(scanner, calculatorService, history);
                    case 2 -> printHistory(history);
//                    case 3 -> downloadCsv(history);
                    default -> System.out.println("1~4 사이의 숫자를 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자로 입력해주세요.");
                scanner.nextLine();
            } catch (CustomException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void runCalculation(Scanner scanner, CalculatorService service, History history) {

        System.out.print("첫 번째 숫자: ");
        int a = validateInput(scanner);

        System.out.print("연산자 (+, -, *, /): ");
        Operator operator = validateOperator(scanner.next());

        System.out.print("두 번째 숫자: ");
        int b = validateInput(scanner);

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

    //연산자 입력 검증
    private static Operator validateOperator(String symbol) {
        return Arrays.stream(Operator.values())
                .filter(op -> op.getOperator().equals(symbol))
                .findFirst()
                .orElseThrow(() -> new CustomException(ExceptionType.INVALID_OPERATOR));
    }

    //숫자 입력 검증
    private static int validateInput(Scanner scanner) {
        long input = scanner.nextLong();
        if (input > Integer.MAX_VALUE || input < Integer.MIN_VALUE) {
            throw new CustomException(ExceptionType.INTEGER_OVERFLOW);
        }
        return (int) input;
    }
}
