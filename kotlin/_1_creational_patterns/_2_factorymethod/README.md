# 팩토리 메서드 패턴

팩토리 메서드는 객체를 생성하는 메서드에 관한 디자인 패턴이다.

그런데 객체를 생성하는 메서드가 왜 필요한가? 객체를 생성하기 위해 생성자가 있는 것 아닌가?

생성자만으로는 한계가 있기 때문이다.

```kotlin
fun createPiece(notation: String): ChessPiece {
	val (type, file, rank) = notation.toCharArray()
	
	return when (type) {
		'q' -> Queen(file, rank)
		'p' -> Pawn(file, rank)
		// ...
		else -> throw RuntimeException("알 수 없는 기물 종류: $type")
	}
}
```

인터페이스 구현체 중 하나를 인스턴스화 한다. 이게 바로 팩토리 메서드 디자인 패턴의 역할이다.

## 정적 팩토리 메서드

정적 팩토리 메서드는 조슈아 블로쉬의 책 “이펙티브 자바” 덕분에 유명해졌다.

```java
Long l1 = new Long("1"); // 생성자
Long l2 = Long.valueOf("1"); // 정적 팩토리 메서드
```

간단한 생성자 대신에 정적 팩토리 메서드 다자인 패턴을 사용하는 이유는 무엇인가?

- 다양한 생성자에 명시적인 이름을 붙일 수 있다. 클래스에 생성자가 많을 경우에 특히 유용하다.
- 일반적으로 생성자에서는 예외가 발생하지 않으리라는 기대가 있다. 그러나 클래스 인스턴스 생성이 절대 실패하지 않는 것은 아니다. 예외가 불가피하다면 생성자보다는 일반적인 메서드에서 발생하는 편이 훨씬 낫다.
- 생성자에 기대하는 것이 한 가지 더 있다면 빠르다는 것이다. 그러나 생성하는 데에 시간이 오래 걸릴 수밖에 없는 객체도 있다. 그런 경우 생성자 대신 정적 팩토리 메서드를 고려하라.

이 세가지는 대체로 스타일 측면의 장점이다. 하지만 정적 팩토리 메서드에는 기술적인 장점도 있다.

### 캐시

Long도 캐시를 한다. valueOf() 함수는 모든 값에 대해서 항상 객체를 반환하는 대신 이미 파싱한 적 있는 값인지 확인한다. 만약 파싱한 적이 있다면 캐시된 객체를 반환한다.

### 하위 클래스 생성

### 코틀린에서 정적 팩토리 메서드 구현하기

자바에서는 정적 팩토리 메서드는 static으로 선언한다. 그러나 코틀린에는 그런 키워드가 없다. 대신 인스턴스에 속하지 않는 메서드는 동반 객체 내부에 선언할 수 있다.

```java
class Server(port: Long) {
    init {
        println("$port 포트에서 서버가 시작됐습니다.")
    }
    
    companion object {
        fun withPort(port: Long) = Server(port)
    }
}
```

자바의 정적 메서드와 마찬가지로 동반 객체는 해당 클래스에 처음 접근할 때 게으르게 생성된다.

중요

```java
하나의 클래스에는 1개의 동반 객체만 존재할 수 있다.
```

인스턴스가 정적 팩토리 메서드를 통해서만 생성되기를 원할 때도 있다. 그럴 땐 객체의 기본 생성자를 private으로 선언하면 된다.

```kotlin
class Server private constructor(port: Long) {
```

```kotlin
fun main() {
    val server1 = Server(8080) // 컴파일 에러
    val server2 = Server.withPort(8080) // 성공!
}
```
