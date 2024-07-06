declare class BehavioralModule {
    static startTracking(): void
    static stopTracking(): void
}

declare class BehavioralNavigation {
    static updateScreen(screen: String): void
}
export {BehavioralModule, BehavioralNavigation};