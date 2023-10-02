package _1_creational_patterns.prototype

data class User(
    val name: String,
    val role: Role,
    private val permissions: Set<String>,
    val tasks: List<String>,
)

enum class Role {
    ADMIN,
    SUPER_ADMIN,
    REGULAR_USER,
}

val allUsers = mutableListOf<User>()

fun createUser(name: String, role: Role) {
    for (u in allUsers) {
        if (u.role == role) {
            allUsers += u.copy(name = name)
        }
    }
    // 같은 권한을 갖는 다른 사용자가 존재하지 않는 경우 처리
}