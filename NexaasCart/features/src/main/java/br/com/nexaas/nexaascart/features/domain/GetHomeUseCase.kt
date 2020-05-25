package br.com.nexaas.nexaascart.features.domain

import br.com.nexaas.nexaascart.features.data.DataResponse
import br.com.nexaas.nexaascart.features.data.repository.HomeRepository

class GetHomeUseCase(private val repository: HomeRepository) {

    suspend operator fun invoke(): List<DataResponse> {
        return repository.get()
    }
}
