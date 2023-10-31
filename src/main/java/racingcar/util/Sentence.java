package racingcar.util;

public enum Sentence {
    INPUT_CAR_NAMES("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
    INPUT_TRY_NUMBERS("시도할 횟수는 몇회인가요?"),
    ALIGN_LINE("\n"),
    RESULT_START_MENTION("실행 결과");

    private final String message;

    Sentence(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
