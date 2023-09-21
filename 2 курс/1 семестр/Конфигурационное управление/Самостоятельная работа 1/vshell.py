import sys
import zipfile

class VShell:
    def __init__(self, filesystem_archive: str):
        self.currentpath = ""
        self.filesystem = zipfile.ZipFile(filesystem_archive)
        self.filesystemlist = self.filesystem.filelist
        
    def start(self):
        while True:
            cmd = input(f'vshell:{self.currentpath}/$ ').split(" ")
            
            if cmd[0] == "ls":
                self.ls()
                
            if cmd[0] == "cd":
                self.cd(cmd[1])
            
            if cmd[0] == 'exit':
                break
            
            
    def getPath(self, path: str):
        for curpath in path:
            if curpath == "/":
                path = path[1:]
            else:
                break
        return path
    
    def pwd(self):
        pass
    
    def ls(self):
        path = self.getPath(self.currentpath)
        for file in self.filesystemlist:
            if path in file.filename:
                file_names = file.filename[len(path):].split("/")  # разбиение имен которые идут после
                # пути, в котором мы находимся
                file_names = list(filter(None, file_names))  # удаление пустых строк из списка
                if len(file_names) > 1 or not file_names:  # пропускаем повторы
                    continue
                print(file_names[0])
    
    def cd(self, newpath):
        if "root:" in newpath:
            self.currentpath = newpath[len('root:'):]
        else:
            self.currentpath += "/" + newpath
            
        if "../" in self.currentpath:
            local_path = local_path[:len(local_path) - len(local_path.split("/")[-1]) - 1]
            return True


    
    def cat(self):
        pass
    
    
if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Usage: python vshell.py <filesystem_archive>")
    else:
        vshell = VShell(sys.argv[1])
        vshell.start()