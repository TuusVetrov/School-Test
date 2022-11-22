class Player(name:String = "",
             attack: Int = 1,
             protection: Int = 1,
             healthPoint: Int = 0,
             damage: IntRange = IntRange(1,1)): Entity(name, attack, protection, healthPoint, damage)
{
    private var amountOfHeal = 3

    fun useHeal(): String {
        if (_healthPoint > _maxHealthPoint / 2)
            return "No need for treatment"

        if (amountOfHeal > 0) {
            amountOfHeal--
            _healthPoint += _maxHealthPoint / 2

            return "You used heal. Now your HP = $_healthPoint"
        }

        return  "You no longer have heals"
    }
}