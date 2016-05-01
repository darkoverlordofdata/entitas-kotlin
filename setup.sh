#!/usr/bin/env bash

# generate scaffolding for the demo

entitas init com.darkoverlordofdata.entitas.demo

entitas create -c Bounds radius:Float
entitas create -c Bullet
entitas create -c Destroy
entitas create -c Enemy
entitas create -c Expires delay:Float
entitas create -c Firing
entitas create -c Health currentHealth:Float maximumHealth:Float
entitas create -c Layer ordinal:com.darkoverlordofdata.entitas.demo.Layer
entitas create -c Player
entitas create -c Position x:Float y:Float
entitas create -c Resource name:String
entitas create -c Scale x:Float y:Float
entitas create -c Score value:Int
entitas create -c SoundEffect effect:com.darkoverlordofdata.entitas.demo.Effect
entitas create -c Tint r:Float g:Float b:Float a:Float
entitas create -c Tween min:Float max:Float speed:Float repeat:Boolean active:Boolean
entitas create -c Velocity x:Float y:Float
entitas create -c View sprite:com.badlogic.gdx.graphics.g2d.Sprite

entitas create -s CollisionSystem IInitializeSystem IExecuteSystem ISetPool
entitas create -s DestroySystem IExecuteSystem ISetPool
entitas create -s EntitySpawningTimerSystem IInitializeSystem IExecuteSystem ISetPool
entitas create -s ExpiringSystem IExecuteSystem ISetPool
entitas create -s HealthRenderSystem IExecuteSystem ISetPool
entitas create -s MovementSystem IExecuteSystem ISetPool
entitas create -s PlayerInputSystem IInitializeSystem IExecuteSystem ISetPool
entitas create -s RemoveOffscreenShipsSystem IExecuteSystem ISetPool
entitas create -s ScoreRenderSystem IInitializeSystem IExecuteSystem ISetPool
entitas create -s SoundEffectSystem IInitializeSystem IExecuteSystem ISetPool
entitas create -s SpriteRenderSystem IExecuteSystem ISetPool
entitas create -s TweenSystem IExecuteSystem ISetPool
entitas create -s ViewManagerSystem ISetPool

