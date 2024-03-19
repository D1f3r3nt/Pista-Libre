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
    
    static func sortDates(_ date1: String, _ date2: String) -> Bool {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd/MM/yyyy-HH:mm"
        
        guard let date1 = dateFormatter.date(from: date1),
                  let date2 = dateFormatter.date(from: date2) else {
                      return false
            }
            return date1 < date2
    }
    
    private static func padStart(value: String, size: Int) -> String {
        String(String(value.reversed()).padding(toLength: size, withPad: "0", startingAt: 0).reversed())
    }
}
