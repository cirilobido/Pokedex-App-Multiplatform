package domain.model

enum class OrderByEnum(val title: String) {
    NUMBER_LOWEST("Low to high"),
    NUMBER_HIGHEST("High to low"),
    A_Z("A-Z"),
    Z_A("Z-A")
}