//
//  Canvas.swift
//  test
//
//  Created by Kiril Krechko on 6.04.22.
//

import UIKit

// MARK: - Canvas
class Canvas: UIView {

	// MARK: - Properties
	private var lines = [[CGPoint]]()
	private var drawerColor: UIColor = .systemGreen
	
	// MARK: - Override methods
	override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
		lines.append([CGPoint]())
	}

	override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
		guard let point = touches.first?.location(in: nil) else { return }

		guard var lastLine = lines.popLast() else { return }
		lastLine.append(point)

		lines.append(lastLine)

		setNeedsDisplay()
	}

	override func draw(_ rect: CGRect) {
		super.draw(rect)

		guard let context = UIGraphicsGetCurrentContext() else { return }

		context.setStrokeColor(drawerColor.cgColor)
		context.setLineWidth(5)
		context.setLineCap(.butt)

		lines.forEach { line in
			for (index, point) in line.enumerated() {
				if index == 0 {
					context.move(to: point)
				} else {
					context.addLine(to: point)
				}
			}
		}

		context.strokePath()
	}
	
	// MARK: - Public methods
	func setDrawerColor(color: UIColor) {
		drawerColor = color
	}
}

