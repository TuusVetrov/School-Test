abstract class Entity()
{
    private var _name = ""
        get() = field.capitalize()

    private var _attack = 0
        set(value) {
            if (isAttackAndProtectionValid(value))
                field = value
            else
                throw IllegalArgumentException("Attack value must be between 1 and 20")
        }

    private var _protection = 0
        set(value) {
            if (isAttackAndProtectionValid(value))
                field = value
            else
                throw IllegalArgumentException("Protection value must be between 1 and 20")
        }

    var _healthPoint = 0
        protected set(value) {
            if (value >= 0)
                field = value
            else
                throw IllegalArgumentException("HP must be between 0 and N")
        }

    private var _damage = IntRange(0,0)
        set(value) {
            if (value.first > 0)
                field = value
            else
                throw IllegalArgumentException("Damage value must be between 0 and N")
        }

    protected var _maxHealthPoint = 0

    constructor(name: String,
                attack: Int,
                protection: Int,
                healthPoint: Int,
                damage: IntRange) : this() {
        _name = name
        _attack = attack
        _protection = protection
        _healthPoint = healthPoint
        _damage = damage
        _maxHealthPoint = healthPoint
    }

    fun attackEntity(opponent: Entity): Int {
        val attackModifier = _attack - opponent._protection + 1
        var attemptNumber = 0
        var damageToOpponent = 0

        do {
            if(isAttackSuccessful()) {
                damageToOpponent = _damage.shuffled().first()

                if(damageToOpponent < opponent._healthPoint)
                    opponent._healthPoint -= damageToOpponent
                else
                    opponent._healthPoint = 0

                break
            }

            attemptNumber++
        }while (attemptNumber < attackModifier)

        return damageToOpponent
    }

    fun statistic(): String {
        return "$_name | Attack: $_attack | Protection: $_protection | HP: $_healthPoint | damage: $_damage"
    }

    fun isAlive(): Boolean {
        return _healthPoint > 0
    }

    private fun isAttackSuccessful(): Boolean {
        val diceNumber = (1..6).shuffled().first()
        return (5..6).contains(diceNumber)
    }

    private fun isAttackAndProtectionValid(value: Int): Boolean {
        return (1..20).contains(value)
    }
}