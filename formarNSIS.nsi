RequestExecutionLevel admin
;--------------------------------
;Incluimos el Modern UI

  !include "MUI2.nsh"

;--------------------------------
;Propiedades de la interfaz

  !define MUI_ABORTWARNING
  !define NOMBREAPP "Formar"

;--------------------------------
#General

;Nombre de la aplicación y del ejecutable
   Name "${NOMBREAPP}"
   Icon "formar.ico"
   OutFile "InstaladorFormar.exe"

;Directorio de instalación
   DirText "Elija un directorio donde instalar la aplicación:"
   InstallDir "$PROGRAMFILES\${NOMBREAPP}"

;Obtenemos el directorio del registro (si esta disponible)
   InstallDirRegKey HKCU "Software\Formar" ""
  
;Indicamos que cuando la instalación se complete no se cierre el instalador automáticamente
   AutoCloseWindow false

;Si se encuentran archivos existentes se sobreescriben
   SetOverwrite on
   SetDatablockOptimize on

;--------------------------------
#Paginas
;páginas referentes al instalador
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  !insertmacro MUI_PAGE_INSTFILES

;páginas referentes al desinstalador
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES

;--------------------------------
#Lenguajes
;Definimos el idioma del instalador
  !insertmacro MUI_LANGUAGE "Spanish"

;--------------------------------

#Secciones

Section "Formar" formar

  SetOutPath "$INSTDIR"
;Hacemos que esta seccion tenga que instalarse obligatoriamente
  SectionIn RO 

;Incluimos todos los archivos que componen nuestra aplicación

  ;Archivos a instalar (solo archivos, los ejecutables van en la sección "prerequisitos"
  File formar.jar
  File "formar.ico"
  File /r reportes
  File /r formar_lib
  File "script.sql"
  File "config.properties"
  File /r imagenes

;Menu inicio
  SetShellVarContext all
  createDirectory "$SMPROGRAMS\${NOMBREAPP}"
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\${NOMBREAPP}.lnk" "$INSTDIR\formar.jar" "" "$INSTDIR\formar.ico"
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\Manual.lnk" "$INSTDIR\manual.pdf" "" ""
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\Desinstalar.lnk" "$INSTDIR\Uninstall.exe" "" ""
    
;Acceso directo en el escritorio
  CreateShortCut "$DESKTOP\${NOMBREAPP}.lnk" "$INSTDIR\${NOMBREAPP}.jar" "" "$INSTDIR\formar.ico"
  
;Hacemos que la instalación se realice para todos los usuarios del sistema
  SetShellVarContext all

;Guardamos un registro de la carpeta instalada
  WriteRegStr HKCU "Software\Formar" "" $INSTDIR
  
;Creamos un desintalador
  WriteUninstaller "$INSTDIR\Uninstall.exe"
  
  AccessControl::GrantOnFile  "$INSTDIR" "(S-1-5-32-545)" "FullAccess"
  
SectionEnd


#Seccion desinstalador

Section "Uninstall"

SetShellVarContext all

;Borramos el ejecutable del menú inicio
  delete "$SMPROGRAMS\${NOMBREAPP}\${NOMBREAPP}.lnk"
  delete "$SMPROGRAMS\${NOMBREAPP}\Manual.lnk"
  delete "$SMPROGRAMS\${NOMBREAPP}\Desinstalar.lnk"

;Borramos el acceso directo del escritorio
  delete "$DESKTOP\${NOMBREAPP}.lnk"

;Intentamos borrar el menú inicio (Solo se puede hacer si la carpeta está vacío)
  rmDir "$SMPROGRAMS\${NOMBREAPP}"
 
;Archivos a desinstalar
    delete $INSTDIR\formar.jar
    delete $INSTDIR\manual.pdf
    delete $INSTDIR\formar.ico
	delete $INSTDIR\java.exe
	delete $INSTDIR\mysql.msi
	;delete $INSTDIR\netframework.exe
	delete $INSTDIR\config.properties
	delete $INSTDIR\script.sql
    rmDir /r $INSTDIR\reportes
    rmDir /r $INSTDIR\formar_lib
	rmDir /r $INSTDIR\imagenes
 
;Borramos el desinstalador
  delete $INSTDIR\Uninstall.exe
 
;Intentamos borrar la carpeta de instalación (Solo se puede si está vacía)
  rmDir $INSTDIR

  DeleteRegKey /ifempty HKCU "Formar"
  
SectionEnd


#Seccion Prerequisitos, ejecucion de otros instaladores 

Section "Prerequisitos" prerequisitos

SectionIn RO

; .net framework lo pedia otro mysql.msi installer, el nuevo no lo requiere.
;DetailPrint "Comenzando la instalacion de .NET Framework"
	;File "netframework.exe"
	;ExecWait '"$INSTDIR\netframework.exe" /q'

DetailPrint "Comenzando la instalacion de Mysql Server"     
    File "mysql.msi"
    ExecWait 'msiexec /i "$INSTDIR\mysql.msi" /passive'
	
DetailPrint "Configurando el Mysql Server"
	ExecWait '"C:\Program Files\MySQL\MySQL Server 5.5\bin\MySQLInstanceConfig.exe" -i -q ServerType=DEVELOPMENT DatabaseType=MIXED ConnectionUsage=DSS Port=3306 ServiceName=MySQL55 RootPassword=root'
	; con el configure parece no hace falta esto..
	;ExecWait 'start "" "C:\Program Files\MySQL\MySQL Server 5.5\bin\mysqld.exe" --initialize'
	nsExec::ExecToLog 'cmd /C ""C:\Program Files\MySQL\MySQL Server 5.5\bin\mysql.exe" --user="root" --password="root" mysql < "$INSTDIR\script.sql""'
	
	
DetailPrint "Comenzando la instalacion de Java"     
    File "java.exe"
    ExecWait '"$INSTDIR\java.exe" /s'
	
SectionEnd

#Seccion "Manual de usuario"

Section "Manual de usuario" manual

SetOutPath "$INSTDIR"

;Archivos a instalar
  File manual.pdf

SectionEnd


;--------------------------------
#Descripciones

  ;Descripcion de Formar
  LangString DESC_Formar ${LANG_SPANISH} "Archivos necesarios para la ejecución de la Formar"

  ;Descripcion de Prerequisitos
  LangString DESC_Prerequisitos ${LANG_SPANISH} "Archivos necesarios para que Formar funcione correctamente"
  
  ;Descripcion de Manual
  LangString DESC_Manual ${LANG_SPANISH} "Manual de usuario"

  ;Asignamos las descripciones a cada seccion
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${Formar} $(DESC_Formar)
    !insertmacro MUI_DESCRIPTION_TEXT ${Prerequisitos} $(DESC_Prerequisitos)
	!insertmacro MUI_DESCRIPTION_TEXT ${Manual} $(DESC_Manual)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END

;--------------------------------