package Builders

class Director(
    private val builder: Builder,
    private val id: String?,
    val login: String,
    val password: String,
    private val isHashed: Boolean,
    private val money: Int = 0
) {
    fun make() {
        builder.setUUID(id)
        builder.setUserLogin(login)
        builder.setUserPassword(password, isHashed)
        if (builder is VisitorBuilder) {
            builder.setMoney(money)
        }
    }
}