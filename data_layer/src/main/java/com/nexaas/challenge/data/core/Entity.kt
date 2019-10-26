package com.nexaas.challenge.data.core

import com.nexaas.challenge.domain.core.DomainMappable
import com.nexaas.challenge.domain.core.DomainModel

abstract class Entity<T: DomainModel>: DomainMappable<T>