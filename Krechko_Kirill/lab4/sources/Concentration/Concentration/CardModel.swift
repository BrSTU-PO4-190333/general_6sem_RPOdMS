//
//  CardModel.swift
//  Concentration
//

import Foundation

struct CardModel { 
    var isFaceUp = false
    var isMatched = false
    var id: Int
    
    private static var idFactory = 0
    
    init() {
        self.id = CardModel.getUniqueIdentifier()
    }
    
    private static func getUniqueIdentifier() -> Int {
        idFactory += 1
        return idFactory
    }
}
