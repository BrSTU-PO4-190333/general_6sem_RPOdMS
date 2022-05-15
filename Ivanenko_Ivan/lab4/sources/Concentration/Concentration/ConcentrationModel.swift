//
//  ConcentrationModel.swift
//  Concentration
//

import Foundation

struct ConcentrationModel {
    
    private(set) var gameScore = 0
    private(set) var cardsArray = [CardModel]()
    
    private var indexOnlyOneFacedUpCard: Int? {
        get {
            var foundIndex: Int?
            cardsArray.indices.forEach {
                guard cardsArray[$0].isFaceUp else { return }
                foundIndex = foundIndex == nil ? $0 : nil
            }
            return foundIndex
        }
        set {
            cardsArray.indices.forEach {
                cardsArray[$0].isFaceUp = ($0 == newValue)
            }
        }
    }
    
    init(numberOfCardPairs: Int) {
        guard numberOfCardPairs > 0 else { return }
        (1...numberOfCardPairs).forEach { _ in
            let card = CardModel()
            let matchingCard = card
            cardsArray += [card, matchingCard]
        }
        
        cardsArray.shuffle()
        gameScore = 0
    }
    
    mutating func chooseCard(at index: Int) {
        guard cardsArray.indices.contains(index) else { return }
        if !cardsArray[index].isMatched {
            if let matchIndex = indexOnlyOneFacedUpCard, matchIndex != index {
                if cardsArray[matchIndex].id == cardsArray[index].id {
                    cardsArray[matchIndex].isMatched = true
                    cardsArray[index].isMatched = true
                    gameScore += 2
                } else {
                    gameScore -= 1
                }
                cardsArray[index].isFaceUp = true
            } else {
                indexOnlyOneFacedUpCard = index
            }
        }
    }
}
