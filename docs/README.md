# 시퀀스 기능
- [x] 자동차 이름을 입력받는다. - InputView$inputCarNames()
- [x] 자동차 이름을 검증한다. - CarNamesValidator$getValidatedCarNamesList(String inputtedString)
  - [x] 자동차 이름을 쉼표(,)를 기준으로 구분한다. - CarNamesValidator$validateSplit(String inputtedCarNames)
  - [x] 쉼표 앞뒤로 공백은 자동으로 없애서 입력받는다. - CarNamesValidator$trimInputtedString(String inputtedCarNames)
  - [x] 이름은 5자 이하만 가능하다. -> 아니면 `IllegalArgumentException` - CarNamesValidator$validateLessThanLength(String carName)
    - [x] 공백을 제거한 후의 길이가 5자 이하로 계산한다. - CarNamesValidator$validateLessThanLength(String carName)
- [x] 입력받은 자동차 이름을 보관한다.
  - [x] 각 자동차는 처음은 이동 거리가 0으로 세팅돼야한다.
- [ ] 사용자로부터 몇 번의 이동을 할 것인지 입력받는다.
  - [ ] 숫자로 입력받아야 한다. -> 아니면 `IllegalArgumentException`
- [ ] 입력받은 시도 횟수만큼 0~9 사이의 무작위 값을 구한 후 4 이상인 경우 전진한다.
  - [ ] 4 이상의 결과가 나온 수만큼 이동 거리가 1씩 증가된다.
  - [ ] 각 차수별로 전진한 양을 출력한다.
    - [ ] 첫 차수 출력하기 전에는 "\n실행 결과"도 출력한다. 
    - [ ] 전진한 양이 없으면 "[$자동차 이름] : "으로 공백으로 출력한다.
    - [ ] 한 차수의 출력이 끝나면 공백을 만들어준다.
- [ ] 누가 우승했는지 계산 후 출력한다.
  - [ ] 우승자는 한 명 이상일 수 있다.
  - [ ] 모두 한 칸도 전진 안 한 상태면 우승했다고 할 수 없다고 (스스로) 판단한다. => 공백으로 출력!
  - [ ] 출력 형식 : "\n최종 우승자 : [$자동차 이름][, [$자동차 이름]].."


# 객체 모델

## 1. RacingController
### State
- RacingService
- InputView
- OutputView

### Behavior
- run()

## 2. RacingService
### State
- RacingCars
- Client

### Behavior
- construct(CarsDto carsDto, Client client)
- `private` void initializeRacingCars(CarsDto carsDto)
- ResultDto playCarRacing()

## 3. InputView
### State
- `Enum` Sentence
- OutputView

### Behavior
- String inputCarNames()
- String inputTryNumber()

## 4. OutputView
### State
- `Enum` Sentence

### Behavior
- void beforeInputCarNames()
- void beforeInputTryNumber()
- void printResult(ResultDto)
- `private` void printRacingDistanceResults(ResultDto)
- `private` void printFinalWinners(ResultDto)

## 5. RacingCars
### State
- List<Car> cars
- int currentRacingRound
- ResultDto result

### Behavior
- `construct`(CarsDto)
- void race() 
- ResultDto endFinalRound()
- boolean isFinalRound()

## 6. Car
### State
- String name
- int distance

### Behavior
- Car createCar(String name)
- int playOneRoundAndGetDistance()

## 7. Client
### State
- int tryNumber

### Behavior
- construct(String inputtedTryNumber)
- `private` void validateInputtedNumber(String inputtedTryNumber)
- int getTotalRounds()

## 8. CarsDto
### State
- List<String> carNames

### Behavior
- `construct`(List<String> inputtedCarNames)
- String getSingleCarName(int index)
- int getNumberOfCars() 

## 9. ResultDto
### State
- List<SingleRoundResultDto> roundResults
- int finalRound
- List<String> finalWinners

### Behavior
- int getFinalRound
- SingleRoundResultDto getSingleRoundResult()
- List<String> finalWinners getFinalWinners()

## 10. SingleRoundResultDto
### State
- int round
- LinkedHashMap<String, Integer> distanceMap

### Behavior
- construct(int round)
- void putDistanceByCarName(String carName, int distance)
- int getRound()
- int getDistanceByCarName(String carName)
- List<String> calculateWinners()

## 11. Validator
### Behavior
- void throwIllegalArgumentException(String message)
- void throwIllegalArgumentException(String message, Throwable t)

## 12. CarNamesValidator extends Validator
### Behavior
- `private` List<String> validateSplit(String inputtedCarNames)
- `private` String trimInputtedString(String inputtedCarNames)
- `private` void validateLessThanLength(String carName)
- String getValidatedCarNamesList(String inputtedString)

## 13. ClientValidator extends Validator
### Behavior
- `private` int validateCastToInt(String inputtedString)
- `private` void validateOneDigit(int value)
- int getValidatedIntValue(int validatedValue)

## 14. `Enum` Sentence
- INPUT_CAR_NAMES("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
- INPUT_TRY_NUMBERS("시도할 횟수는 몇회인가요?")
- RACING_RESULT_START("실행 결과")
- SINGLE_CAR_WITHOUT_DISTANCE("%s : ")
- FINAL_WINNERS("최종 우승자 :")

## 15. `Enum` Error
- LONG_CAR_NAME_ERROR("자동차 이름이 5자를 초과했습니다.")
- BLANK_CAR_NAME_ERROR("자동차 이름을 공백으로 입력하였습니다.")
- String CANNOT_CAST_TO_INT = "int형으로 변환할 수 없습니다. (범위 초과)"
- String NOT_ONE_DIGIT = "입력한 숫자가 한 자리 자연수가 아닙니다."