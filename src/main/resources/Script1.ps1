param(
[string]$browserName=$(throw "Parameter missing: - browser browserName (pass name of browser that will be run tests)"), 
[string]$pathProjectDirectory=$(throw "Parameter missing: - pathProjectDirectory to project)")
)

$config = join-path $pathProjectDirectory 'src\main\resources\cofig.properties' 

$content = Get-Content $config
$content.Replace("browser","$browserName")
$content = Set-Content $config

