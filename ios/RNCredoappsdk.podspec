
Pod::Spec.new do |s|
  s.name         = "RNCredoappsdk"
  s.version      = "2.0.0"
  s.summary      = "RNCredoappsdk"
  s.description  = <<-DESC
                  RNCredoappsdk
                   DESC
  s.homepage     = "https://credolab.com"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "akinbolawa90@gmail.com" }
  s.platform     = :ios, "11.0"
  s.source       = { :path => "." }
  s.source_files  = "*.{h,m,swift}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386 arm64' }
  s.swift_version = '5.0'

    s.preserve_paths = 'CredoAppCore.xcframework', 'CredoAppMusic.xcframework', 'CredoAppContacts.xcframework', 'CredoAppCalendarReminders.xcframework', 'CredoAppCalendarEvents.xcframework', 'CredoAppMedia.xcframework', 'CredoAppIovation.xcframework', 'FraudForce.xcframework'
  s.xcconfig = { 'OTHER_LDFLAGS' => '-framework CredoAppCore -framework CredoAppMusic -framework CredoAppContacts -framework CredoAppCalendarReminders -framework CredoAppCalendarEvents -framework CredoAppMedia -framework CredoAppMedia  -framework CredoAppIovation  -framework FraudForce' }
  s.vendored_frameworks = 'CredoAppCore.xcframework', 'CredoAppMusic.xcframework', 'CredoAppContacts.xcframework', 'CredoAppCalendarReminders.xcframework', 'CredoAppCalendarEvents.xcframework', 'CredoAppMedia.xcframework', 'CredoAppIovation.xcframework', 'FraudForce.xcframework'
  
  s.dependency "RNCredoappsdk"
end

  
