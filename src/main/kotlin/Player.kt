class Player(name:String = "",
             attack: Int = 1,
             protection: Int = 1,
             healthPoint: Int = 0,
             damage: IntRange = IntRange(1,1)): Entity(name, attack, protection, healthPoint, damage)
{
    private var amountOfHeal = 3

    fun useHeal() {
        if (_healthPoint < _maxHealthPoint / 2)
            println("No need for treatment")

        if (amountOfHeal > 0) {
            amountOfHeal--
            _healthPoint += _maxHealthPoint / 2
        }
        else {
            println("You no longer have heals")
        }
    }
}