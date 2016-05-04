package com.darkoverlordofdata.entitas.test

import com.darkoverlordofdata.bunny.TestIt
import com.darkoverlordofdata.entitas.*

fun main(args: Array<String>) {

    val pool = Pool(Component.TotalComponents.ordinal, 0, { Component.values()[it].name })
    val e1: Entity = pool.createEntity("player")
    val e2: Entity = pool.createEntity("test2")
    val e3: Entity = pool.createEntity("test3")
    val e0: Entity = pool.createEntity("Hello")


    TestIt("Entitas-kotlin unit test").run {
        it("1st entity") {
            e1.addBounds(1f)
                    .addHealth(100f, 100f)
                    .setPlayer(true)
                    .addResource("Fighter")

            it.equals(e1.creationIndex, 0)
        }

        it("2nd entity") {
            e2.addPosition(1f, 1f)
            it.equals(e2.toString(), "Entity_test2(1)(Position)")

        }

        it("3rd entity") {
            var k = 0
            e3.onComponentAdded += {e: EntityChangedArgs ->
                k += 1
                if (k == 1) {
                    it.equals(e3.toString(), "Entity_test3(2)(Position)")
                } else {
                    it.equals(e3.toString(), "Entity_test3(2)(Position,Velocity)")
                }

            }
            e3.addPosition(0f, 0f)
            e3.addVelocity(0f, 0f)
        }

        it("Pool sizes") {
            it.equals(pool.count, 3)
            it.equals(pool.retainedEntitiesCount, 0)
            it.equals(pool.reusableEntitiesCount, 0)
        }

        it("1 component matcher") {
            val m1 = Matcher.allOf(Matcher.Position)
            it.equals(m1.matches(e2), true)

            val m2 = Matcher.allOf(Matcher.Position, Matcher.Velocity)
            it.equals(m2.matches(e2), false)

        }

        it("2 component matcher") {
            val m1 = Matcher.allOf(Matcher.Position)
            it.equals(m1.matches(e3), true)
            it.equals(m1.toString(), "AllOf(Bounds)")

            val m2 = Matcher.allOf(Matcher.Position, Matcher.Velocity)
            it.equals(m2.matches(e3), true)
            it.equals(m2.toString(), "AllOf(Bounds,Bullet)")

        }

        it("Groups") {
            val m2 = Matcher.allOf(Matcher.Position)
            val g2 = pool.getGroup(m2)
            val s2 = g2.entities
            it.equals(2, s2.size)

            val g = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.Velocity))
            val s = g.entities
            it.equals(1, s.size)

        }

        it("Groups") {
            e3.onComponentRemoved+= {e:EntityChangedArgs ->
                it.equals(e3.toString(), "Entity_test3(2)(Velocity)")
            }
            e3.removePosition()

        }

        it("Groups") {
            e0.addHealth(10.0f, 10.0f)
            it.equals(e0.creationIndex, 3)
            it.equals(e0.toString(), "Entity_Hello(3)(Health)")

            val g7 = pool.getGroup(Matcher.allOf(Matcher.Health))
            val s7 = g7.entities
            it.equals(s7.size, 2)

            pool.onEntityDestroyed += {e: PoolEntityChangedArgs ->
                it.equals(pool.count, 3)
                it.equals(pool.retainedEntitiesCount, 0)
                it.equals(pool.reusableEntitiesCount, 0)
            }

            it.equals(pool.reusableEntitiesCount, 0)
            pool.destroyEntity(e0)
            it.equals(pool.reusableEntitiesCount, 1)

        }

    }
}
