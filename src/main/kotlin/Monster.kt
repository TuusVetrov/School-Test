class Monster(name:String = "",
              attack: Int = 1,
              protection: Int = 1,
              healthPoint: Int = 0,
              damage: IntRange = IntRange(1,1)) : Entity(name, attack, protection, healthPoint, damage)
{

}