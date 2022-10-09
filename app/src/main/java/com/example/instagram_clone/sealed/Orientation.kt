package com.example.instagram_clone.sealed

sealed class Orientation {
    object Vertical : Orientation()
    object Horizontal : Orientation()
}