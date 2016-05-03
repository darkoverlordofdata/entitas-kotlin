package com.darkoverlordofdata.entitas.test

import com.darkoverlordofdata.bunny.Bunny
import com.darkoverlordofdata.entitas.*

enum class Effect {
    PEW,
    ASPLODE,
    SMALLASPLODE
}

enum class Layer {
    DEFAULT,
    BACKGROUND,
    ENEMY3,
    ENEMY2,
    ENEMY1,
    PLAYER,
    BATTLE
}


fun main(args: Array<String>) {
    UnitTest().run()
}


class UnitTest : Bunny() {
    lateinit var e0: Entity
    lateinit var e1: Entity
    lateinit var e2: Entity
    lateinit var e3: Entity

    val pool = Pool(Component.TotalComponents.ordinal, 0, {Component.values()[it].name})

    init {
        describe("Entitas-kotlin unit test")

        test("1st entity") {
            e1 = pool.createEntity("player")
                    .addBounds(1f)
                    .addHealth(100f, 100f)
                    .setPlayer(true)
                    .addResource("Fighter")

            assert.equal(e1.creationIndex, 0)
        }

        test("2nd entity") {
            e2 = pool.createEntity("test2")
            e2.addPosition(1f, 1f)
            assert.equal(e2.toString(), "Entity_test2(1)(Position)")

        }

        test("3rd entity") {
            var k = 0
            e3 = pool.createEntity("test3")
            e3.onComponentAdded += {e:EntityChangedArgs ->
                k += 1
                if (k == 1) {
                    assert.equal(e3.toString(), "Entity_test3(2)(Position)")
                } else {
                    assert.equal(e3.toString(), "Entity_test3(2)(Position,Velocity)")
                }

            }
            e3.addPosition(0f, 0f)
            e3.addVelocity(0f, 0f)
        }

        test("Pool sizes") {
            assert.equal(pool.count, 3)
            assert.equal(pool.retainedEntitiesCount, 0)
            assert.equal(pool.reusableEntitiesCount, 0)
        }

        test("1 component matcher") {
            val m1 = Matcher.allOf(Matcher.Position)
            assert.equal(m1.matches(e2), true)

            val m2 = Matcher.allOf(Matcher.Position, Matcher.Velocity)
            assert.equal(m2.matches(e2), false)

        }

        test("2 component matcher") {
            val m1 = Matcher.allOf(Matcher.Position)
            assert.equal(m1.matches(e3), true)
            assert.equal(m1.toString(), "AllOf(Bounds)")

            val m2 = Matcher.allOf(Matcher.Position, Matcher.Velocity)
            assert.equal(m2.matches(e3), true)
            assert.equal(m2.toString(), "AllOf(Bounds,Bullet)")

        }

        test("Groups") {
            val m2 = Matcher.allOf(Matcher.Position)
            val g2 = pool.getGroup(m2)
            val s2 = g2.entities
            assert.equal(2, s2.size)

            val g = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.Velocity))
            val s = g.entities
            assert.equal(1, s.size)

        }

        test("Groups") {
            e3.onComponentRemoved+= {e:EntityChangedArgs ->
                assert.equal(e3.toString(), "Entity_test3(2)(Velocity)")
            }
            e3.removePosition()

        }

        test("Groups") {
            e0 = pool.createEntity("Hello")
            e0.addHealth(10.0f, 10.0f)
            assert.equal(e0.creationIndex, 3)
            assert.equal(e0.toString(), "Entity_Hello(3)(Health)")

            val g7 = pool.getGroup(Matcher.allOf(Matcher.Health))
            val s7 = g7.entities
            assert.equal(s7.size, 2)

            pool.onEntityDestroyed += {e:PoolEntityChangedArgs ->
                assert.equal(pool.count, 3)
                assert.equal(pool.retainedEntitiesCount, 0)
                assert.equal(pool.reusableEntitiesCount, 0)
            }

            assert.equal(pool.reusableEntitiesCount, 0)
            pool.destroyEntity(e0)
            assert.equal(pool.reusableEntitiesCount, 1)

        }

    }

 }

