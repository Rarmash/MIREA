import sys
import zipfile

class VShell:
    def __init__(self, filesystem_archive: str):
        """VShell object constructor.

        Args:
            filesystem_archive (str): Path to filesystem
        """
        self.currentpath = ""
        self.filesystem = zipfile.ZipFile(filesystem_archive)
        self.filesystemlist = self.filesystem.filelist
        
    def start(self):
        """
        VShell initialization
        """
        while True:
            cmd = input(f'vshell:{self.currentpath}/$ ').split(" ")
            
            if cmd[0].lower() == "ls":
                try:
                    if not self.ls(cmd[1]):
                        print(f'Directory "{cmd[1]}" does not exist.')
                except:
                    self.ls("")
                
            elif cmd[0].lower() == "cd":
                try:
                    if not self.cd(cmd[1]):
                        print(f'Directory "{cmd[1]}" does not exist.')
                except:
                    self.cd("")
                    
            elif cmd[0].lower() == "pwd":
                self.pwd()
            
            elif cmd[0].lower() == "cat":
                try:
                    if not self.cat(cmd[1]):
                        print(f'Error while opening {cmd[1]}.')
                except:
                    self.cat("")
            
            elif cmd[0].lower() == 'exit':
                break
            
            else:
                print('Unknown command.')
    
    def pwd(self):
        """
        Displays the current working directory.
        """
        print(f'/root{self.currentpath}/')
    
    def ls(self, newpath: str):
        """
        Get files list of current directory.
        """
        path = self.currentpath
        if "/root" in newpath:
            path = newpath.replace('/root/', '')
        else:
            path += "/" + newpath
        if path != "":
            if path[0] == "/":
                path = path[1:]
        flag = False
        for file in self.filesystemlist:
            if path in file.filename:
                flag = True
        if flag == False:
            return False
        for file in self.filesystemlist:
            if path in file.filename:
                files = file.filename[len(path):].split("/")
                files = list(filter(None, files)) # Deleting empty strings
                if len(files) > 1 or not files: # If repeating
                    continue
                print(files[0])
        return True
            
    
    def cd(self, newpath: str):
        """A function to navigate to a specific directory within VShell.

        Args:
            newpath (str): The relative or absolute path in the directory to which you want to move.

        Returns:
            Bool: True if success else False if path does not exist.
        """
        if "/root" in newpath: # If absolute path
            newpath = newpath.replace('/root/', '')
            for file in self.filesystemlist:
                if newpath in file.filename:
                    self.currentpath = "/" + newpath
                    return True
        elif ".." in newpath: # If moving to directory ahead
            try:
                while self.currentpath[-1] != "/":
                    self.currentpath = self.currentpath[:-1]
            except IndexError:
                return False
            self.currentpath = self.currentpath[:-1]
            return True
        elif newpath == "": # If current directory
            pass
        else:
            for file in self.filesystemlist:
                if newpath in file.filename:
                    if file.is_dir():
                        self.currentpath += "/" + newpath
                        return True
        return False
          
    def cat(self, filename: str):
        """Reading a specific file.

        Args:
            filename (str): Filename what must be opened.

        Returns:
            Bool: True if success else False if path does not exist.
        """
        path = self.currentpath
        if "/root" in filename:
            filename = filename.replace('/root/', '')
            for file in self.filesystemlist:
                if filename in file.filename:
                    path = "/" + filename
        else:
            path += "/" + filename
        if path != "":
            if path[0] == "/":
                path = path[1:]
        else:
            return False
        try:
            with self.filesystem.open(path, 'r') as f:
                lines = [x.decode('utf8').strip() for x in f.readlines()]
                for line in lines:
                    print(line)
            return True
        except KeyError:
            return False
    
if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Usage: python vshell.py <filesystem_archive>")
    else:
        vshell = VShell(sys.argv[1])
        vshell.start()