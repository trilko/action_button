package com.ironsource.domain.verifiers

interface VerifiersFactory {

    fun createVerifiers(): List<Verifier>

    class BaseVerifiersFactory(
        private val verifiers: List<Verifier>
    ) : VerifiersFactory {
        override fun createVerifiers(): List<Verifier> = verifiers
    }
}