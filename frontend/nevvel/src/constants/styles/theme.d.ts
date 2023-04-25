import "styled-components";

declare module "styled-components" {
  export interface DefaultTheme {
    breakpoints: {
      small: string;
      medium: string;
      large: string;
    };
    color: {
      text: string;
      background: string;
      main: string;
      sub: string;
      point: string;
    };
  }
}
