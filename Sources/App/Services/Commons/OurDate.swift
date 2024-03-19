import Foundation

final class OurDate {
    
    static func getCurrentDate() -> String {
        let date = Date()
        let calendar = Calendar.current
        
        
        let year = calendar.component(.year, from: date)
        let month = calendar.component(.month, from: date)
        let day = calendar.component(.day, from: date)
        
        let hour = calendar.component(.hour, from: date)
        let minute = calendar.component(.minute, from: date)
        
        let monthFormat = padStart(value: String(month), size: 2)
        let dayFormat = padStart(value: String(day), size: 2)
        
        return "\(dayFormat)/\(monthFormat)/\(year)-\(hour):\(minute)"
    }
    
    private static func padStart(value: String, size: Int) -> String {
        String(String(value.reversed()).padding(toLength: size, withPad: "0", startingAt: 0).reversed())
    }
}
