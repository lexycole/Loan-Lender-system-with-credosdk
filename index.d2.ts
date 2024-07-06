declare class CredoAppService {
    setIgnorePermissions(ignore: boolean): void
    setForceResolvePermissions(ignore: boolean): void
    addModuleAsync(module: any): void
    collectAsync(): Promise<string> 
}

export default CredoAppService;