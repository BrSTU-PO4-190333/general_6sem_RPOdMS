//
//  ViewController.swift
//  Concentration
//

import UIKit

final class ViewController: UIViewController {
    
    private var themes = [
        "Halloween": ["🦇", "😱", "🙀", "😈", "🎃", "👻", "🍭", "🍬", "🍎"],
        "Sport": ["⚽️", "🏀", "🏈", "⚾️", "🥎", "🏐", "🏉", "🎾", "🏓"],
        "Emoji": ["🤬", "🤯", "🥵", "😜", "🤩", "😂", "🥶", "😶‍🌫️", "🤢"],
        "Animals": ["🐶", "🐱", "🐭", "🐹", "🦊", "🐰", "🐻", "🐼", "🐻‍❄️"],
    ]

    private var numberOfCardPairs: Int {
        return (cardButtons.count + 1) / 2
    }
    
    private lazy var game = ConcentrationModel(numberOfCardPairs: numberOfCardPairs)
    private var currentTheme = [String]()
    private var emojiDictionary = [Int: String]()

    @IBOutlet private weak var flipCountLabel: UILabel!
    @IBOutlet private var cardButtons: [UIButton]!
    
    @IBAction private func startNewGame(_ sender: UIButton) {
        game = ConcentrationModel(numberOfCardPairs: (cardButtons.count + 1) / 2)
        flipCountLabel.text = "Score: \(game.gameScore)"
        updateTheme()
        updateViewFromModel()
    }
    
    @IBAction private func touchCard(_ sender: UIButton) {
        guard let cardNumber = cardButtons.firstIndex(of: sender) else { return }
        game.chooseCard(at: cardNumber)
        updateViewFromModel()
        flipCountLabel.text = "Score: \(game.gameScore)"
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        updateTheme()
    }
    
    private func updateTheme() {
        guard let key = themes.keys.randomElement(),
              let theme = themes[key] else { return }
        currentTheme = theme
    }
    
    private func emojiForCard(for card: CardModel) -> String {
        if emojiDictionary[card.id] == nil, !currentTheme.isEmpty {
            guard let randomIndex = (0..<currentTheme.count).randomElement() else { return "?" }
            emojiDictionary[card.id] = currentTheme.remove(at: randomIndex)
        }
        return emojiDictionary[card.id] ?? "?"
    }
    
    private func updateViewFromModel() {
        cardButtons.indices.forEach {
            let button = cardButtons[$0]
            let card = game.cardsArray[$0]
            
            if card.isFaceUp {
                button.setTitle(emojiForCard(for: card), for: .normal)
                button.backgroundColor = .white
            } else {
                button.setTitle("", for: .normal)
                button.backgroundColor = card.isMatched ? .clear : .orange
            }

            button.isUserInteractionEnabled = !(card.isMatched || card.isFaceUp)
        }
    }
}

