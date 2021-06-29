package com.example.cmsbackend

import arrow.core.Either
import strikt.api.Assertion
import strikt.assertions.isA

inline fun <reified U, reified T> Assertion.Builder<Either<U, T>>.isRight() =
    isA<Either.Right<T>>()
        .get { value }

inline fun <reified U, reified T> Assertion.Builder<Either<U, T>>.isLeft() =
    isA<Either.Left<U>>()
        .get { value }
