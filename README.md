# 구조

### **Data**

Remote, Local 등 다양한 데이터 소스의 처리를 담당하는 영역입니다.

ApiModel은 별도의 Mapper를 통해 DomainModel로 변환하여 전달합니다.

데이터 전달은 Repository를 통해 이루어짐으로써 SSOT 원칙을 준수할 수 있도록 하였습니다.

### Domain

앱의 비즈니스 로직과 관련된 부분을 다루는 계층입니다.

- Repository를 정의하는 interface
- Domain Model
- Usecase

세 가지 패키지로 구분되어 있습니다.

Repository Interface는 Domain 레이어에 분리하여 실제 구현은 data 레이어에서 이루어짐으로써 data 레이어에 대한 직접적인 의존성을 피할 수 있습니다.

Domain Model 은 앱의 비즈니스 로직 구현에 필요한 모델을 별도로 정의한 부분입니다. Data 영역의 Entity나 DTO에 직접적으로 의존하는 것을 피하여 API나 DB 변경사항에 비교적 안정적으로 대응할 수 있습니다.

Usecase는 앱에서 자주 사용되는 비즈니스 로직을 별도의 클래스로 분리한 영역입니다. 다만, 복잡하거나 중복된 비즈니스 로직을 사용하는 경우가 없기 때문에 별도로 구현하지 않았습니다.

### Presentation

앱의 UI를 표현하는 View나 ViewModel, 안드로이드 의존성을 가지는 클래스 들을 다루는 영역입니다.

UI 이벤트는 Sealed Class 및 SharedFlow를 사용하여 ViewModel에서 처리할 수 있게끔 하였습니다.

UI 로직 중 별도의 비즈니스 로직이 필요하지 않은 경우는 바로 View(Activity…) 에서 처리를 하였습니다.

---

# DI

의존성 주입은 Hilt를 사용하였습니다.

기존에 현업에서는 별도 Container Class를 만드는 Mannual 방식을 사용하고 있었으나,

최근 Hilt로 이전을 하고 있습니다. 이에 본 과제에서도 Hilt를 사용한 DI를 적용하였습니다.

Dispatcher Qualifier를 사용하여 IO, Default중 어떤 Dispatcher를 주입할지 Hilt에 알려줄 수 있도록 하였습니다.

---

# Testing

평소 현업에서는 별도 테스트코드를 작성하지는 못하고 있는 상황이지만,

짧은 기간 안에 테스트에 대한 개념을 공부하여 테스트코드를 작성해 보았습니다.

테스트 더블은 Fake를 사용하였고, 전체 코드 Coverage는 Line을 기준으로 20%대입니다.

핵심 비즈니스 로직을 검증할 수 있도록 ViewModel 에 중점을 두어 테스트 코드를 작성하였습니다.
