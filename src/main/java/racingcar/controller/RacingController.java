package racingcar.controller;

import racingcar.domain.CarsDto;
import racingcar.service.RacingService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    RacingService racingService;

    public void run() {
        outputView.beforeInputCarNames();
        String inputtedCarNames = inputView.inputCarNames();

        CarsDto carsDto = new CarsDto(inputtedCarNames);
        racingService = new RacingService(carsDto);

        outputView.beforeInputTryNumber();
        String inputtedTryNumber = inputView.inputTryNumber();
    }
}
