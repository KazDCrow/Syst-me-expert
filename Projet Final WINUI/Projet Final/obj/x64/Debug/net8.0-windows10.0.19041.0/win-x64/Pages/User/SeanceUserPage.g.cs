﻿#pragma checksum "D:\Projet Final - Programmation graphique - Automne 2024\Projet Final\Projet Final\Pages\User\SeanceUserPage.xaml" "{8829d00f-11b8-4213-878b-770e8597ac16}" "1ECC2222818BF26C11BCBC9F57E993113510C9A3610E0E7FBA274FDE3EA6AB5E"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Projet_Final.Pages.User
{
    partial class SeanceUserPage : 
        global::Microsoft.UI.Xaml.Controls.Page, 
        global::Microsoft.UI.Xaml.Markup.IComponentConnector
    {
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.UI.Xaml.Markup.Compiler"," 3.0.0.2411")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
        private static class XamlBindingSetters
        {
            public static void Set_Microsoft_UI_Xaml_Controls_TextBlock_Text(global::Microsoft.UI.Xaml.Controls.TextBlock obj, global::System.String value, string targetNullValue)
            {
                if (value == null && targetNullValue != null)
                {
                    value = targetNullValue;
                }
                obj.Text = value ?? global::System.String.Empty;
            }
        };

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.UI.Xaml.Markup.Compiler"," 3.0.0.2411")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
        private partial class SeanceUserPage_obj5_Bindings :
            global::Microsoft.UI.Xaml.IDataTemplateExtension,
            global::Microsoft.UI.Xaml.Markup.IDataTemplateComponent,
            global::Microsoft.UI.Xaml.Markup.IXamlBindScopeDiagnostics,
            global::Microsoft.UI.Xaml.Markup.IComponentConnector,
            ISeanceUserPage_Bindings
        {
            private global::Projet_Final.Classes.Seance dataRoot;
            private bool initialized = false;
            private const int NOT_PHASED = (1 << 31);
            private const int DATA_CHANGED = (1 << 30);
            private bool removedDataContextHandler = false;

            // Fields for each control that has bindings.
            private global::System.WeakReference obj5;
            private global::Microsoft.UI.Xaml.Controls.TextBlock obj6;
            private global::Microsoft.UI.Xaml.Controls.TextBlock obj7;
            private global::Microsoft.UI.Xaml.Controls.TextBlock obj8;
            private global::Microsoft.UI.Xaml.Controls.TextBlock obj9;
            private global::Microsoft.UI.Xaml.Controls.TextBlock obj10;

            // Static fields for each binding's enabled/disabled state
            private static bool isobj6TextDisabled = false;
            private static bool isobj7TextDisabled = false;
            private static bool isobj8TextDisabled = false;
            private static bool isobj9TextDisabled = false;
            private static bool isobj10TextDisabled = false;

            public SeanceUserPage_obj5_Bindings()
            {
            }

            public void Disable(int lineNumber, int columnNumber)
            {
                if (lineNumber == 53 && columnNumber == 36)
                {
                    isobj6TextDisabled = true;
                }
                else if (lineNumber == 54 && columnNumber == 36)
                {
                    isobj7TextDisabled = true;
                }
                else if (lineNumber == 55 && columnNumber == 36)
                {
                    isobj8TextDisabled = true;
                }
                else if (lineNumber == 56 && columnNumber == 36)
                {
                    isobj9TextDisabled = true;
                }
                else if (lineNumber == 57 && columnNumber == 36)
                {
                    isobj10TextDisabled = true;
                }
            }

            // IComponentConnector

            public void Connect(int connectionId, global::System.Object target)
            {
                switch(connectionId)
                {
                    case 5: // Pages\User\SeanceUserPage.xaml line 43
                        this.obj5 = new global::System.WeakReference(global::WinRT.CastExtensions.As<global::Microsoft.UI.Xaml.Controls.Grid>(target));
                        break;
                    case 6: // Pages\User\SeanceUserPage.xaml line 53
                        this.obj6 = global::WinRT.CastExtensions.As<global::Microsoft.UI.Xaml.Controls.TextBlock>(target);
                        break;
                    case 7: // Pages\User\SeanceUserPage.xaml line 54
                        this.obj7 = global::WinRT.CastExtensions.As<global::Microsoft.UI.Xaml.Controls.TextBlock>(target);
                        break;
                    case 8: // Pages\User\SeanceUserPage.xaml line 55
                        this.obj8 = global::WinRT.CastExtensions.As<global::Microsoft.UI.Xaml.Controls.TextBlock>(target);
                        break;
                    case 9: // Pages\User\SeanceUserPage.xaml line 56
                        this.obj9 = global::WinRT.CastExtensions.As<global::Microsoft.UI.Xaml.Controls.TextBlock>(target);
                        break;
                    case 10: // Pages\User\SeanceUserPage.xaml line 57
                        this.obj10 = global::WinRT.CastExtensions.As<global::Microsoft.UI.Xaml.Controls.TextBlock>(target);
                        break;
                    default:
                        break;
                }
            }
                        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.UI.Xaml.Markup.Compiler"," 3.0.0.2411")]
                        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
                        public global::Microsoft.UI.Xaml.Markup.IComponentConnector GetBindingConnector(int connectionId, object target) 
                        {
                            return null;
                        }

            public void DataContextChangedHandler(global::Microsoft.UI.Xaml.FrameworkElement sender, global::Microsoft.UI.Xaml.DataContextChangedEventArgs args)
            {
                 if (this.SetDataRoot(args.NewValue))
                 {
                    this.Update();
                 }
            }

            // IDataTemplateExtension

            public bool ProcessBinding(uint phase)
            {
                throw new global::System.NotImplementedException();
            }

            public int ProcessBindings(global::Microsoft.UI.Xaml.Controls.ContainerContentChangingEventArgs args)
            {
                int nextPhase = -1;
                ProcessBindings(args.Item, args.ItemIndex, (int)args.Phase, out nextPhase);
                return nextPhase;
            }

            public void ResetTemplate()
            {
                Recycle();
            }

            // IDataTemplateComponent

            public void ProcessBindings(global::System.Object item, int itemIndex, int phase, out int nextPhase)
            {
                nextPhase = -1;
                switch(phase)
                {
                    case 0:
                        nextPhase = -1;
                        this.SetDataRoot(item);
                        if (!removedDataContextHandler)
                        {
                            removedDataContextHandler = true;
                            var rootElement = (this.obj5.Target as global::Microsoft.UI.Xaml.Controls.Grid);
                            if (rootElement != null)
                            {
                                rootElement.DataContextChanged -= this.DataContextChangedHandler;
                            }
                        }
                        this.initialized = true;
                        break;
                }
                this.Update_(global::WinRT.CastExtensions.As<global::Projet_Final.Classes.Seance>(item), 1 << phase);
            }

            public void Recycle()
            {
            }

            // ISeanceUserPage_Bindings

            public void Initialize()
            {
                if (!this.initialized)
                {
                    this.Update();
                }
            }
            
            public void Update()
            {
                this.Update_(this.dataRoot, NOT_PHASED);
                this.initialized = true;
            }

            public void StopTracking()
            {
            }

            public void DisconnectUnloadedObject(int connectionId)
            {
                throw new global::System.ArgumentException("No unloadable elements to disconnect.");
            }

            public bool SetDataRoot(global::System.Object newDataRoot)
            {
                if (newDataRoot != null)
                {
                    this.dataRoot = global::WinRT.CastExtensions.As<global::Projet_Final.Classes.Seance>(newDataRoot);
                    return true;
                }
                return false;
            }

            // Update methods for each path node used in binding steps.
            private void Update_(global::Projet_Final.Classes.Seance obj, int phase)
            {
                if (obj != null)
                {
                    if ((phase & (NOT_PHASED | (1 << 0))) != 0)
                    {
                        this.Update_Nom_activite(obj.Nom_activite, phase);
                        this.Update_Type_activite(obj.Type_activite, phase);
                        this.Update_DateStr(obj.DateStr, phase);
                        this.Update_Heure(obj.Heure, phase);
                        this.Update_Appreciation_general(obj.Appreciation_general, phase);
                    }
                }
            }
            private void Update_Nom_activite(global::System.String obj, int phase)
            {
                if ((phase & ((1 << 0) | NOT_PHASED )) != 0)
                {
                    // Pages\User\SeanceUserPage.xaml line 53
                    if (!isobj6TextDisabled)
                    {
                        XamlBindingSetters.Set_Microsoft_UI_Xaml_Controls_TextBlock_Text(this.obj6, obj, null);
                    }
                }
            }
            private void Update_Type_activite(global::System.String obj, int phase)
            {
                if ((phase & ((1 << 0) | NOT_PHASED )) != 0)
                {
                    // Pages\User\SeanceUserPage.xaml line 54
                    if (!isobj7TextDisabled)
                    {
                        XamlBindingSetters.Set_Microsoft_UI_Xaml_Controls_TextBlock_Text(this.obj7, obj, null);
                    }
                }
            }
            private void Update_DateStr(global::System.String obj, int phase)
            {
                if ((phase & ((1 << 0) | NOT_PHASED )) != 0)
                {
                    // Pages\User\SeanceUserPage.xaml line 55
                    if (!isobj8TextDisabled)
                    {
                        XamlBindingSetters.Set_Microsoft_UI_Xaml_Controls_TextBlock_Text(this.obj8, obj, null);
                    }
                }
            }
            private void Update_Heure(global::System.String obj, int phase)
            {
                if ((phase & ((1 << 0) | NOT_PHASED )) != 0)
                {
                    // Pages\User\SeanceUserPage.xaml line 56
                    if (!isobj9TextDisabled)
                    {
                        XamlBindingSetters.Set_Microsoft_UI_Xaml_Controls_TextBlock_Text(this.obj9, obj, null);
                    }
                }
            }
            private void Update_Appreciation_general(global::System.Double obj, int phase)
            {
                if ((phase & ((1 << 0) | NOT_PHASED )) != 0)
                {
                    // Pages\User\SeanceUserPage.xaml line 57
                    if (!isobj10TextDisabled)
                    {
                        XamlBindingSetters.Set_Microsoft_UI_Xaml_Controls_TextBlock_Text(this.obj10, obj.ToString(), null);
                    }
                }
            }
        }

        /// <summary>
        /// Connect()
        /// </summary>
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.UI.Xaml.Markup.Compiler"," 3.0.0.2411")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
        public void Connect(int connectionId, object target)
        {
            switch(connectionId)
            {
            case 2: // Pages\User\SeanceUserPage.xaml line 13
                {
                    this.mainGrid = global::WinRT.CastExtensions.As<global::Microsoft.UI.Xaml.Controls.Grid>(target);
                }
                break;
            case 3: // Pages\User\SeanceUserPage.xaml line 40
                {
                    this.lv_liste_seances = global::WinRT.CastExtensions.As<global::Microsoft.UI.Xaml.Controls.ListView>(target);
                }
                break;
            case 11: // Pages\User\SeanceUserPage.xaml line 58
                {
                    global::Microsoft.UI.Xaml.Controls.Button element11 = global::WinRT.CastExtensions.As<global::Microsoft.UI.Xaml.Controls.Button>(target);
                    ((global::Microsoft.UI.Xaml.Controls.Button)element11).Click += this.btn_delete_Click;
                }
                break;
            default:
                break;
            }
            this._contentLoaded = true;
        }


        /// <summary>
        /// GetBindingConnector(int connectionId, object target)
        /// </summary>
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.UI.Xaml.Markup.Compiler"," 3.0.0.2411")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
        public global::Microsoft.UI.Xaml.Markup.IComponentConnector GetBindingConnector(int connectionId, object target)
        {
            global::Microsoft.UI.Xaml.Markup.IComponentConnector returnValue = null;
            switch(connectionId)
            {
            case 5: // Pages\User\SeanceUserPage.xaml line 43
                {                    
                    global::Microsoft.UI.Xaml.Controls.Grid element5 = (global::Microsoft.UI.Xaml.Controls.Grid)target;
                    SeanceUserPage_obj5_Bindings bindings = new SeanceUserPage_obj5_Bindings();
                    returnValue = bindings;
                    bindings.SetDataRoot(element5.DataContext);
                    element5.DataContextChanged += bindings.DataContextChangedHandler;
                    global::Microsoft.UI.Xaml.DataTemplate.SetExtensionInstance(element5, bindings);
                    global::Microsoft.UI.Xaml.Markup.XamlBindingHelper.SetDataTemplateComponent(element5, bindings);
                }
                break;
            }
            return returnValue;
        }
    }
}

