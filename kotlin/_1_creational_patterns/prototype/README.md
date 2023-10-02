# 프로토타입 패턴(Prototype Pattern)

프로토타입 디자인 패턴은 유사하면서도 조금 다른 객체를 그때그때 목적에 맞게 생성하기 위해 사용한다.

## 프로토타입에서 시작하기

프로토타입의 핵심 아이디어는 객체를 쉽게 복사할 수 있도록 하는 것. 적어도 다음의 두 가지 경우에 프로토타입 패턴이 필요하다.

- 객체 생성에 많은 비용이 드는 경우(예를 들어 객체 생성 시 데이터베이스에서 자료를 조회해야 하는 경우)
- 비슷하지만 조금씩 다른 객체를 생성하느라 비슷한 코드를 매번 반복하고 싶지 않은 경우

코틀린에서 모든 데이터 클래스는 copy() 메서드를 가진다. 이 메서드는 다른 데이터 클래스의 인스턴스를 받아 복제본을 생성하며, 원한다면 그 과정에서 속성을 변경할 수도 있다.

```kotlin
fun createUser(name: String, role: Role) {
    for (u in allUsers) {
        if (u.role == role) {
            allUsers += u.copy(name = name)
        }
    }
    // 같은 권한을 갖는 다른 사용자가 존재하지 않는 경우 처리
```

비공개 속성까지 모두 그대로 복사된다.

데이터 클래스는 굉장히 유용한 기능이다.