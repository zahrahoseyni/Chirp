import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init () {
        // If this still fails, replace InitKoinKt with the Kotlin file name + "Kt"
        // that declares doInitKoin, e.g., KoinKt.doInitKoin()
        InitKoinKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
