//
//  StudentInfo.swift
//  Lesson24
//
//  Created by Harbros 109 on 3.01.22.
//

import Foundation

// MARK: - StudentInfoOutput

protocol StudentInfoOutput: AnyObject { }

// MARK: - StudentInfoInput

protocol StudentInfoInput: AnyObject {
    func showStudentInfo(student: Student)
}
